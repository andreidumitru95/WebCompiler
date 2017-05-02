package org.shared;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Costin on 5/2/2017.
 */
public class LanguageStrings {
    public static final String cpp = " #include <iostream.h>\n" +
                                        "\n" +
                                        "main()\n" +
                                        "{\n" +
                                        "    cout << \"Hello World!\";\n" +
                                        "    return 0;\n" +
                                        "}";

    public static  final String c="#include<stdio.h>\n" +
                                    "\n" +
                                    "main()\n" +
                                    "{\n" +
                                    "    printf(\"Hello World\");\n" +
                                    "}";
    List<String> cpp_key_word= new ArrayList<>(java.util.Arrays.asList(
        "alignas",
            "alignof",
            "and",
            "and_eq",
            "asm",
            "atomic_cancel", //(TM TS)
            "atomic_commit", //(TM TS)
            "atomic_noexcept", //(TM TS)
            "auto",
            "bitand",
            "bitor",
            "bool",
            "break",
            "case",
            "catch",
            "char",
            "char16_t",// (since C++11)
            "char32_t", //(since C++11)
            "class",
            "compl",
            "concept",// (concepts TS)
            "const",
            "constexpr", //(since C++11)
            "const_cast",
            "continue",
            "decltype", //(since C++11)
            "default",
            "do",
            "double",
            "dynamic_cast",
            "else",
            "enum",
            "explicit",
            "export",
            "extern",
            "false",
            "float",
            "for",
            "friend",
            "goto",
            "if",
            "import" ,//(modules TS)
            "inline",
            "int",
            "long",
            "module" ,//(modules TS)
            "mutable",//(1)
            "namespace",
            "new",
            "noexcept",// (since C++11)
            "not",
            "not_eq",
            "nullptr",//(since C++11)
            "operator",
            "or",
            "or_eq",
            "private",
            "protected",
            "public",
            "register",//(2)
            "reinterpret_cast",
            "requires",// (concepts TS)
            "return",
            "short",
            "signed",
            "sizeof",//(1)
            "static",
            "static_assert",//(since C++11)
            "static_cast",
            "struct",//(1)
            "switch",
            "synchronized", //(TM TS)
            "template",
            "this",
            "thread_local",// (since C++11)
            "throw",
            "true",
            "try",
            "typedef",
            "typeid",
            "typename",
            "union",
            "unsigned",
            "using",//(1)
            "virtual",
            "void",
            "volatile",
            "wchar_t",
            "while",
            "xor",
            "xor_eq"
    ));

}
