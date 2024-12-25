package com.immomo.momo;

import java.util.Set;

public final class Constants {

    public static final String ADD_ANNOTATION = "com.meituan.robust.patch.annotaion.Add";
    public static final String ARRAY_TYPE = "[";
    public static final String ASPECTJ_AROUND_CLASS = "org.aspectj.runtime.internal.AroundClosure";
    public static Class AddAnnotationClass = null;
    public static final String BOOLEAN = "boolean";
    public static final String BYTE = "byte";
    public static final String CHAR = "char";
    public static final String CLASSES_DEX_NAME = "classes.dex";
    public static final String CONSTRUCTOR = "Constructor";
    public static final String DEFAULT_MAPPING_FILE = "/robust/mapping.txt";
    public static final String DOUBLE = "double";
    public static final String FLOAT = "float";
    public static final String GET_REAL_PARAMETER = "getRealParameter";
    public static final String INLINE_PATCH_SUFFIX = "InLinePatch";
    public static final String INSERT_FIELD_NAME = "changeQuickRedirect";
    public static final String INT = "int";
    public static final String INTERFACE_NAME = "com.meituan.robust.ChangeQuickRedirect";
    public static final String LAMBDA_MODIFY = "com.meituan.robust.patch.RobustModify";
    public static final String LANG_BOOLEAN = "java.lang.Boolean";
    public static final String LANG_BYTE = "java.lang.Byte";
    public static final String LANG_CHARACTER = "Character";
    public static final String LANG_DOUBLE = "java.lang.Double";
    public static final String LANG_FLOAT = "java.lang.Float";
    public static final String LANG_INT = "java.lang.Integer";
    public static final String LANG_LONG = "java.lang.Long";
    public static final String LANG_SHORT = "java.lang.Short";
    public static final String LANG_VOID = "java.lang.Void";
    public static final String LONG = "long";
    public static final String METHOD_MAP_OUT_PATH = "/outputs/robust/methodsMap.robust";
    public static final String METHOD_MAP_PATH = "/robust/methodsMap.robust";
    public static final String MODIFY_ANNOTATION = "com.meituan.robust.patch.annotaion.Modify";
    public static Class ModifyAnnotationClass = null;
    public static final String ORIGINCLASS = "originClass";
    public static final String PACKNAME_END = ";";
    public static final String PATACH_DEX_NAME = "patch.dex";
    public static final String PATACH_JAR_NAME = "patch.jar";
    public static final String PATCH_CONTROL_SUFFIX = "Control";
    public static final String PATCH_EXECUTE = "patch execute ,other extension will be ignore ";
    public static final String PATCH_PACKAGENAME = "com.meituan.robust.patch";
    public static final String PATCH_SUFFIX = "Patch";
    public static final String PATCH_TEMPLATE_FULL_NAME = "com.meituan.robust.utils.PatchTemplate";
    public static final String PRIMITIVE_TYPE = "ZCBSIJFDV";
    public static  Set RFileClassSet;
    public static final String ROBUST_APK_HASH_FILE_NAME = "robust.apkhash";
    public static final String ROBUST_ASSIST_SUFFIX = "RobustAssist";
    public static final String ROBUST_GENERATE_DIRECTORY = "outputs/robust";
    public static final String ROBUST_PUBLIC_SUFFIX = "RobustPublic";
    public static final String ROBUST_UTILS_FULL_NAME = "com.meituan.robust.utils.EnhancedRobustUtils";
    public static final String ROBUST_XML = "robust.xml";
    public static final String SHORT = "short";
    public static final String SMALI_INVOKE_SUPER_COMMAND = "invoke-super";
    public static final String SMALI_INVOKE_VIRTUAL_COMMAND = "invoke-virtual";
    public static final String STATICFLAG = "staticRobust";
    public static final String VOID = "void";
    public static final String ZIP_FILE_NAME = "meituan.jar";
    public static final String[] LIB_NAME_ARRAY = {"baksmali-2.1.2.jar", "smali-2.1.2.jar", "dx.jar"};
    public static final char OBJECT_TYPE = 'L';
    public static final String PACKNAME_START = String.valueOf(OBJECT_TYPE);
    public static final Boolean OBSCURE = true;
    public static boolean isLogging = true;

    private Constants() {
        throw new UnsupportedOperationException();
    }
}
