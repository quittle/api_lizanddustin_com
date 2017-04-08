# Copyright (c) 2017 Dustin Doloff
# Licensed under Apache License v2.0

load("@bazel_toolbox//rules:rules.bzl",
    "zip_files",
)

filegroup(
    name = "receive_rsvp_sources",
    srcs = glob([ "py/*.py" ]),
)

py_library(
    name = "receive_rsvp",
    srcs = glob([ "py/*.py" ]),
    deps = [
        "@boto3//:boto3",
    ],
)

py_test(
    name = "receive_rsvp_test",
    srcs = glob([ "pytest/*.py" ]),
    main = "pytest/save_rsvp_test.py",
    deps = [
        #"@python_mock//:mock",
        ":receive_rsvp",
    ]
)

zip_files(
    name = "receive_rsvp_zip",
    srcs = [ ":receive_rsvp_sources" ],
    strip_prefixes = [
        "rsvp/py",
    ],
)
