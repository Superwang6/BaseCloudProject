package cn.fudges.oauth2.mode;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;

/**
 * @author 王平远
 * @since 2024/11/29
 */

public class UserDetailDeserializer extends StdDeserializer<UserDetail> {
    protected UserDetailDeserializer() {
        super(UserDetail.class);
    }

    @Override
    public UserDetail deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode treeNode = jsonParser.getCodec().readTree(jsonParser);
        Long id = readJsonNode(treeNode, "id").asLong();
        //TODO


        return null;
    }

    private JsonNode readJsonNode(JsonNode jsonNode, String fieldName) {
        return jsonNode.has(fieldName) ? jsonNode.get(fieldName) : null;
    }

}
