# Copyright (c) 2017 Dustin Doloff
# Licensed under Apache License v2.0

workspace(name = "api_lizanddustin_com")

maven_jar(
    name = "com_amazonaws_aws_lambda_java_core",
    artifact = "com.amazonaws:aws-lambda-java-core:1.1.0",
    sha1 = "b25d4345c6e9e8be78b2803dd43d812f15178a81",
)

maven_jar(
    name = "com_amazonaws_aws_java_sdk_dynamodb",
    artifact = "com.amazonaws:aws-java-sdk-dynamodb:1.11.184",
    sha1 = "2f9b9092a4358f90ef5ed8624ea4a074343528e9",
)

maven_jar(
    name = "org_mockito_mockito_core",
    artifact = "org.mockito:mockito-core:2.10.0",
    sha1 = "871efe6f2607d8c93dd25b8c1fa09851d4286dd6",
)

maven_jar(
    name = "org_objenesis_objenesis",
    artifact = "org.objenesis:objenesis:2.6",
    sha1 = "639033469776fd37c08358c6b92a4761feb2af4b",
)

maven_jar(
    name = "net_bytebuddy_byte_buddy",
    artifact = "net.bytebuddy:byte-buddy:1.7.4",
    sha1 = "d0e77888485e1683057f8399f916eda6049c4acf",
)

maven_jar(
    name = "net_bytebuddy_byte_buddy_agent",
    artifact = "net.bytebuddy:byte-buddy-agent:1.7.4",
    sha1 = "66286677db75148b3c2fb94e72f1c61aceb20e33",
)

git_repository(
    name = "rules_web",
    commit = "63dea1a7b8f7b3c71b326480a5c2628d0838a399",
    remote = "https://github.com/quittle/rules_web.git",
)
load("@rules_web//:rules_web_repositories.bzl", "rules_web_repositories")
rules_web_repositories()

