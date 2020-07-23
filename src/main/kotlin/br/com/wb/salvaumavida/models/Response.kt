package br.com.wb.salvaumavida.models

sealed class Response {
    data class Success(val data: Any) : Response()
    data class Error(val message: String, val cause: String) : Response()
}