# Copyright (c) 2017 Dustin Doloff
# Licensed under Apache License v2.0

workspace(name = "api_lizanddustin_com")

_BOTO3_BUILD_FILE = """

py_library(
    name = "boto3",
    srcs = glob([ "**/*.py" ]),
    visibility = [ "//visibility:public" ],
)

"""

new_http_archive(
    name = "boto3",
    url = "https://pypi.python.org/packages/91/60/649da03299624f524c8d0cd4c6c73c194023e85dd4938f1e7712ab6888bf/boto3-1.4.4-py2.py3-none-any.whl",
    type = "zip",
    sha256 = "5050c29353fec97301116386f469fa5858ccf47201623b53cf9f74e603bda52f",
    build_file_content = _BOTO3_BUILD_FILE,
)

git_repository(
    name = "bazel_toolbox",
    commit = "c8c2a639f96224ba099be13eac147eb4ed5f6375",
    remote = "https://github.com/quittle/bazel_toolbox.git",
)
load("@bazel_toolbox//:bazel_toolbox_repositories.bzl", "bazel_toolbox_repositories")
bazel_toolbox_repositories()

