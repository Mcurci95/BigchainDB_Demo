from bigchaindb_driver import BigchainDB

bdb_root_url = 'http://bdb-server:9984'


bdb = BigchainDB(bdb_root_url)

# Original Asset data that is immutable
bicyle = {
    'data': {
        'Automobile': {
            'serial_number': 'abcd1234',
            'manufacturer': 'Tesla',
            'year_created': '2020',
            'model': 'Model 3'
        },
    },
}

# Metadata that can change between each transfer
metadata = {
    'miles_driven': '3000',
    'accident': 'N',
    'auto_pilot': 'Y'
}
