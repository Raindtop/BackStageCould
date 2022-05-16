package org.raindrop.common.security.serialize;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
@JsonDeserialize(using = OAuth2RefreshTokenDeserializer.class)
public class OAuth2RefreshTokenMixin {

}
