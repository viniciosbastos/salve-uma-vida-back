package br.com.wb.salvaumavida.config

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*

@Component
class JwtTokenUtil : Serializable {
    private val serialVersionUID = -2550185165626007488L
    private val TOKEN_VALIDITY = 1000*60*60

    private val secret: String = "mysecret"

    fun getUsernameFromToken(token: String?): String {
        return getClaimFromToken(token) { obj: Claims? -> obj!!.subject }
    }

    fun getExpirationDateFromToken(token: String?): Date {
        return getClaimFromToken(token) { obj: Claims? -> obj!!.expiration }
    }

    fun <T> getClaimFromToken(token: String?, claimsResolver: (Claims?) -> T): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver(claims)
    }

    private fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body
    }

    private fun isTokenExpired(token: String): Boolean? {
        val expiration: Date = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, Any> = HashMap()
        return doGenerateToken(claims, userDetails.username)
    }

    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, secret).compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = getUsernameFromToken(token)
        return username == userDetails.username && !isTokenExpired(token)!!
    }
}