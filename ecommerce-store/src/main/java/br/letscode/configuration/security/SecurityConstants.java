package br.letscode.configuration.security;

public class SecurityConstants {

    public static final String SECRET = "4453fd5e8408dc24655669d0a37ef72e"; //base para gerar os JWT tokens
    public static final long EXPIRATION_TIME = 1800_000; // 30 mins
    public static final String TOKEN_PREFIX = "Bearer "; //Inicio do JWT (ex: Bearer 0832nkds...)
    public static final String HEADER_STRING = "Authorization"; //Header que o token sera adicionado/lido.

}
