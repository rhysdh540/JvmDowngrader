package xyz.wagyourtail.jvmdg.j16.stub;


import org.objectweb.asm.Opcodes;
import xyz.wagyourtail.jvmdg.version.Ref;
import xyz.wagyourtail.jvmdg.version.Stub;

public class J_L_IndexOutOfBoundsException {
    @Stub(opcVers = Opcodes.V16, ref = @Ref(value = "java/lang/IndexOutOfBoundsException", member = "<init>"))
    public static IndexOutOfBoundsException init(long i) {
        return new IndexOutOfBoundsException("Index out of range: " + i);
    }

}
  