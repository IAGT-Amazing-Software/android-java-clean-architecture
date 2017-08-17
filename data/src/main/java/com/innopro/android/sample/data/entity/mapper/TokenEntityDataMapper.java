package com.innopro.android.sample.data.entity.mapper;


import com.innopro.android.sample.data.entity.TokenEntity;
import com.innopro.android.sample.domain.Token;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Mapper class used to transform {@link TokenEntity} (in the data layer) to {@link Token} in the
 * domain layer.
 */
@Singleton
public class TokenEntityDataMapper {

    @Inject
    public TokenEntityDataMapper() {
    }

    /**
     * Transform a {@link TokenEntity} into an {@link Token}.
     *
     * @param tokenEntity Object to be transformed.
     * @return {@link Token} if valid {@link TokenEntity} otherwise null.
     */
    public Token transform(TokenEntity tokenEntity) {
        Token token = null;
        if (tokenEntity != null) {
            token = new Token(tokenEntity.getValue(), tokenEntity.getExpiresIn(), tokenEntity.getType(), tokenEntity.getScope());
        }

        return token;
    }
}

