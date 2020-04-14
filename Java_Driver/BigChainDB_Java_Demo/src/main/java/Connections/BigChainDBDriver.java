package Connections;

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
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BigChainDBDriver {

    public static final String URL = "http://127.0.0.1:9984";


    public void run(List<Map<String, String>> assetList) throws Exception {
        BigChainDBDriver demo = new BigChainDBDriver();

        demo.SetConfig();

        KeyPair keys = demo.getKeys();

        System.out.println(Base58.encode(keys.getPublic().getEncoded()));
        System.out.println(Base58.encode(keys.getPrivate().getEncoded()));

        MetaData metaData = new MetaData();
        metaData.setMetaData("", "");

        long start = System.nanoTime();

        for (Map<String, String> assetData : assetList) {
            String txID = demo.create(assetData, metaData, keys);
        }
        long end = System.nanoTime();

        long total = end - start;
        System.out.println(total);

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
