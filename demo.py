from bigchaindb_driver import BigchainDB
from bigchaindb_driver.crypto import generate_keypair
from time import sleep

# URL to Docker image
bdb_root_url = 'localhost:9984'


bdb = BigchainDB(bdb_root_url)

# Original Asset data that is immutable
car = {
    'data': {
        'Automobile': {
            'serial_number': 'abcd1234',
            'manufacturer': 'Tesla',
            'year_created': '2020',
            'model': 'Model 3'
        },
    },
}

print(f"Asset:\t{car}")

# Metadata that can change between each transfer
metadata = {
    'miles_driven': '3000',
    'accident': 'N',
    'auto_pilot': 'Y'
}

# Generating a Public and Private key for Marco and Dennis
marco, dennis = generate_keypair(), generate_keypair()
print("\nMarco's keys")
print(f"\tPublic: {marco.public_key}")
print(f"\tPrivate: {marco.private_key}")
print("\nDennis' keys")
print(f"\tPublic: {dennis.public_key}")
print(f"\tPrivate: {dennis.private_key}")


# Creating the asset
prepared_creation_tx = bdb.transactions.prepare(
    operation='CREATE',
    signers=marco.public_key,
    asset=car,
    metadata=metadata,
)

print("\nPrepared transactions: ")
print(prepared_creation_tx)


# Fulfill the creation. Using Marco's private key as a signature
fulfilled_creation_tx = bdb.transactions.fulfill(
    prepared_creation_tx, private_keys=marco.private_key
)

# Sending over fulfilled creation to BigchainDB
sent_creation_tx = bdb.transactions.send_commit(fulfilled_creation_tx)

# Retreive the transactions id
txid = fulfilled_creation_tx['id']
print(f"\ntransactions ID: {txid}")


sleep(10)

print("\nChecking to ensure transactions was successfully included in a block")
block_height = bdb.blocks.get(txid=txid)
print(f"\nBlock height: {block_height}")
block = bdb.blocks.retrieve(str(block_height))
print(f"\nBlock that contains transactions: {block}")

# Transfer
print("Now that the car is created. We can transfer it.")

# New transactions uses the creation_transactions id
creation_tx = bdb.transactions.retrieve(txid)

# Get ID of the asset
asset_id = creation_tx['id']
transfer_asset = {
    'id' : asset_id,
}

output_index = 0
output = creation_tx['outputs'][output_index]
transfer_input = {
    'fulfillment': output['condition']['details'],
    'fulfills': {
        'output_index': output_index,
        'transaction_id': creation_tx['id'],
    },
    'owners_before': output['public_keys'],
}

# Transfer to Dennis
prepared_transfer_tx = bdb.transactions.prepare(
    operation='TRANSFER',
    asset=transfer_asset,
    inputs=transfer_input,
    recipients=dennis.public_key,
)

# Fulfill the transacation by using Marco's private key
fulfilled_transfer_tx = bdb.transactions.fulfill(
    prepared_transfer_tx,
    private_keys=marco.private_key,
)

print("\nFulfilled Transfer")
print(f"\t{fulfilled_transfer_tx}")

sent_transfer_tx = bdb.transactions.send_commit(fulfilled_transfer_tx)