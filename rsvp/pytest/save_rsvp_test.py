# Copyright (c) 2017 Dustin Doloff
# Licensed under Apache License v2.0

#import mock
import os
import rsvp.py.save_rsvp as save_rsvp
import unittest

class MockDynamoDBClient():
    def put_item(self, **kwargs):
        self.last_put_item_call = kwargs


class TestRSVPHandler(unittest.TestCase):
    def test_reduce_object(self):
        reduce_object = save_rsvp.reduce_object
        self.assertEqual({}, reduce_object({}, []))
        self.assertEqual({}, reduce_object({}, ['a']))
        self.assertEqual({'a': 1}, reduce_object({'a': 1}, ['a']))
        self.assertEqual({}, reduce_object({'a': 1}, []))
        self.assertEqual({'a': 1}, reduce_object({'a': 1, 'b': 2}, ['a']))
        self.assertEqual({'a': 1}, reduce_object({'a': 1, 'b': {'a': 1}}, ['a']))
        self.assertEqual({'a': 1, 'b': 2}, reduce_object({'a': 1, 'b': 2}, ['a', 'b']))
        self.assertEqual({'a': 1, 'b': 2}, reduce_object({'a': 1, 'b': 2}, ['a', 'b', 'c']))

    def test_handler(self):
        with self.assertRaises(KeyError):
            save_rsvp.handler(None, None)

        with self.assertRaises(KeyError):
            save_rsvp.handler({}, None)

        region = 'us-east-1'
        table = 'table'

        os.environ['AWS_REGION'] = region
        os.environ['DYNAMO_DB_TABLE_NAME'] = table


        mock_ddb_client = MockDynamoDBClient()
        save_rsvp.get_dynamodb = lambda: mock_ddb_client

        save_rsvp.handler({}, None)

        self.assertEqual({'Item': {}, 'TableName': table}, mock_ddb_client.last_put_item_call)

        save_rsvp.handler({'name': 'Bob', 'Fake': 'Missing'}, None)

        self.assertEqual({'Item': {'name': {'S': 'Bob'}}, 'TableName': table}, mock_ddb_client.last_put_item_call)


if __name__ == '__main__':
    unittest.main()
