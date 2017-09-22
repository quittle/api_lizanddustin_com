# Copyright (c) 2017 Dustin Doloff
# Licensed under Apache License v2.0

load("@rules_web//deploy:deploy.bzl",
    "deploy_lambda_function_script",
)

java_library(
    name = "save_rsvp",
    srcs = glob([ "java/src/**/*.java" ]),
    deps = [
        "@com_amazonaws_aws_java_sdk_core//jar",
        "@com_amazonaws_aws_java_sdk_dynamodb//jar",
        "@com_amazonaws_aws_lambda_java_core//jar",
        "@com_fasterxml_jackson_core_jackson_annotations//jar",
        "@com_fasterxml_jackson_core_jackson_core//jar",
        "@com_fasterxml_jackson_core_jackson_databind//jar",
        "@joda_time_joda_time//jar",
        "@org_apache_commons_logging//jar",
        "@org_apache_httpcomponents_httpclient//jar",
        "@org_apache_httpcomponents_httpcore//jar",
    ],
)

java_test(
    name = "save_rsvp_test",
    srcs = glob([ "java/test/**/*.java" ]),
    test_class = "com.dustindoloff.api_lizanddustin_com.rsvp.SaveRsvpHandlerTest",
    deps = [
        ":save_rsvp",
        "//test:mockito",
        "@com_amazonaws_aws_java_sdk_dynamodb//jar",
    ],
)

deploy_lambda_function_script(
    name = "deploy_save_rsvp",
    function_name = "api_lizanddustin_com__rsvp_post",
    function_handler = "com.dustindoloff.api_lizanddustin_com.rsvp.SaveRsvpHandler::handle",
    function_role = "arn:aws:iam::915290536872:role/rsvp_lambda",
    library = ":save_rsvp",
    language = "java",
    region = "us-east-1",
    memory = 192,
    timeout = 30,
    environment = {
        "DYNAMO_DB_TABLE_NAME": "lizanddustin-rsvp",
    }
)
