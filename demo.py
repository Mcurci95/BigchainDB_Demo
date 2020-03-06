from bigchaindb_driver import BigchainDB
from bigchaindb_driver.crypto import generate_keypair

bdb_root_url = 'http://bdb-server:9984'


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

print(prepared_creation_tx)