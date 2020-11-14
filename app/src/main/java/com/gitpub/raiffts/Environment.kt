package com.gitpub.raiffts


interface Environment {
    val apiUrl: String
    val secretKey: String
    val khabenskyId: String
}

class SandboxEnvironment : Environment {
    override val apiUrl = "https://test.ecom.raiffeisen.ru/api/sbp/v1"
    override val secretKey =
        "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIwMDAwMDMzMzMzMjgwMDItMzMzMjgwMDIiLCJqdGkiOiJkZDhhMjVkNC0wYjc0LTQwNzMtOTVlOS0xYmUzYzYyYTFlYjQifQ.5OVDzHsuFa_nybss6LU8lqBPFUfm-O_SturRQtoXbWo"
    override val khabenskyId = "MA0000000552"
}
