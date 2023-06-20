package xyz.wagyourtail.jvmdg.j11.stub;


import org.objectweb.asm.Opcodes;
import xyz.wagyourtail.jvmdg.version.Ref;
import xyz.wagyourtail.jvmdg.version.Stub;

import java.util.function.Predicate;

public class J_U_F_Predicate {

    @Stub(opcVers = Opcodes.V11, ref = @Ref("Ljava/util/function/Predicate;"))
    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return predicate.negate();
    }

}
