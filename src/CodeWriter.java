/**
 * Created by bradley on 25/08/15.
 */
public class CodeWriter {
    private static int lastUsedIdentifer = 0;

    public static WrittenNode writeCode(FXNode node) {
        String code = "";

        String type = node.getType();
        String identifier = Character.toLowerCase(type.charAt(0)) + type.substring(1) + lastUsedIdentifer++;
        String creation = node.getType() + " " + identifier + " = new " + type + "();\n";
        code += creation;

        // Set attributes
        code += getSetAttributesCode(node, identifier);
        code += getSetChildrenCode(node, identifier);

        return new WrittenNode(code, identifier);
    }

    private static String getSetAttributesCode(FXNode node, String identifier) {
        final String[] attributeCode = {""};
        node.getAttributes().forEach((key, value) -> {
            attributeCode[0] += identifier + ".set" + Character.toUpperCase(key.charAt(0)) + key.substring(1) + "(" + value + ");\n";
        });

        return attributeCode[0];
    }

    private static String getSetChildrenCode(FXNode node, String identifier) {
        final String[] childrenCode = {""};
        node.getChildren().forEach(child -> {
            WrittenNode writtenChild = writeCode(child);
            childrenCode[0] += writtenChild.getCode() + "\n";
            childrenCode[0] += "// Adding child to parent\n";
            childrenCode[0] += identifier + ".getChildren().add(" + writtenChild.getIdentifier() + ");\n";
        });

        return childrenCode[0];
    }

    public static class WrittenNode {
        private String code;
        private String identifier;

        private WrittenNode(String code, String identifier) {
            this.code = code;
            this.identifier = identifier;
        }

        public String getCode() {
            return code;
        }

        private String getIdentifier() {
            return identifier;
        }
    }
}
