# Copyright (c) 2017 Dustin Doloff
# Licensed under Apache License v2.0

import boto3
import os
import utils

REQUEST_KEYS = (
    'name',
    'attendance',
    'contact-type',
    'contact-value',
    'extra',
)

ENVIRONMENT_KEYS = utils.list_to_dict([
    'DYNAMO_DB_TABLE_NAME',
])

def ensure_environment_keys():
    for key in ENVIRONMENT_KEYS.iterkeys():
        # This will raise a KeyError if not found
        os.environ[key]

def get_dynamodb():
    return boto3.client('dynamodb')

def reduce_object(obj, keys):
    return { key: value for key, value in obj.iteritems() if key in keys }

def handler(event, context):
    ensure_environment_keys()

    table_name = os.environ['DYNAMO_DB_TABLE_NAME']
    event = { key: {'S': value } for key, value in reduce_object(event, REQUEST_KEYS).iteritems() }

    get_dynamodb().put_item(TableName=table_name, Item=event)
