# Copyright (c) 2017 Dustin Doloff
# Licensed under Apache License v2.0

ARN_PARTS = {
    'arn': 0,
    'partition': 1,
    'service': 2,
    'region': 3,
    'account-id': 4,
    'resource': 5,
}

def list_to_dict(l):
    return { v: v for v in l }

def _get_arn_part(arn, part):
    return arn.split(':')[ARN_PARTS[part]]

def get_arn_partition(arn):
    return _get_arn_part(arn, 'partition')

def get_arn_service(arn):
    return _get_arn_part(arn, 'service')

def get_arn_region(arn):
    return _get_arn_part(arn, 'region')

def get_arn_account_id(arn):
    return _get_arn_part(arn, 'account-id')

def get_arn_resource(arn):
    return _get_arn_part(arn, 'resource')
