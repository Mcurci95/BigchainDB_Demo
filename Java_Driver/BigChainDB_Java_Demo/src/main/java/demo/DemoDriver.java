package demo;

import com.bigchaindb.builders.BigchainDbConfigBuilder;
import com.bigchaindb.builders.BigchainDbTransactionBuilder;
import com.bigchaindb.constants.Operations;
import com.bigchaindb.model.GenericCallback;
import com.bigchaindb.model.MetaData;
import com.bigchaindb.model.Transaction;
import com.bigchaindb.util.Base58;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import okhttp3.Response;

import java.io.IOException;
import java.security.KeyPair;
import java.util.Map;
import java.util.TreeMap;

public class DemoDriver {

    public static final String URL = "http://127.0.0.1:9984";


    public void run() throws Exception {
        DemoDriver demo = new DemoDriver();

        demo.SetConfig();

        KeyPair keys = demo.getKeys();

        System.out.println(Base58.encode(keys.getPublic().getEncoded()));
        System.out.println(Base58.encode(keys.getPrivate().getEncoded()));

        Map<String, String> assetData = new TreeMap<String, String>() {{
            put("name", "James Bond");
            put("age", "doesn't matter");
            put("purpose", "saving the world");
        }};

        MetaData metaData = new MetaData();
        metaData.setMetaData("", "");

        String txId = demo.create(assetData, metaData, keys);
        Thread.sleep(60 * 1);

    }

    public void SetConfig() {
        BigchainDbConfigBuilder.baseUrl(this.URL).setup();
    }

    public KeyPair getKeys() {
        net.i2p.crypto.eddsa.KeyPairGenerator edDsaKpg = new net.i2p.crypto.eddsa.KeyPairGenerator();
        KeyPair keyPair = edDsaKpg.generateKeyPair();
        System.out.println("(*) Keys Generated..");
        return keyPair;
    }

    public String create(Map<String, String> assetData, MetaData metaData, KeyPair keys) throws Exception {

        try {
            Transaction trans = null;

            trans = BigchainDbTransactionBuilder
                        .init()
                        .addAssets(assetData, TreeMap.class)
                        .operation(Operations.CREATE)
                        .buildAndSign( (EdDSAPublicKey) keys.getPublic(), (EdDSAPrivateKey) keys.getPrivate())
                        .sendTransaction(handleServerResponse());
            System.out.println("(*) CREATE Transaction sent.. - " + trans.getId());
            return trans.getId();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    private GenericCallback handleServerResponse() {
        //define callback methods to verify response from BigchainDBServer
        GenericCallback callback = new GenericCallback() {

            @Override
            public void transactionMalformed(Response response) {
                System.out.println("malformed " + response.message());
                onFailure();
            }

            @Override
            public void pushedSuccessfully(Response response) {
                System.out.println("pushedSuccessfully");
                onSuccess(response);
            }

            @Override
            public void otherError(Response response) {
                System.out.println("otherError" + response.message());
                onFailure();
            }
        };

        return callback;
    }

    private void onSuccess(Response response) {

        System.out.println("Transaction posted successfully");
    }

    private void onFailure() {

        System.out.println("Transaction failed");
    }
}
