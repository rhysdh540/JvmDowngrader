package xyz.wagyourtail.jvmdg.j10.stub;


import org.objectweb.asm.Opcodes;
import xyz.wagyourtail.jvmdg.stub.Stub;

import java.lang.invoke.MethodType;

public class J_L_I_MethodType {


    @Stub(opcVers = Opcodes.V10)
    public static Class<?> lastParameterType(MethodType mt) {
        if (mt.parameterCount() == 0) {
            return void.class;
        }
        return mt.parameterType(mt.parameterCount() - 1);
    }

}