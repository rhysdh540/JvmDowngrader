package xyz.wagyourtail.jvmdg.j10.stub;


import org.objectweb.asm.Opcodes;
import xyz.wagyourtail.jvmdg.version.Ref;
import xyz.wagyourtail.jvmdg.version.Stub;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class J_N_URLEncoder {

    @Stub(opcVers = Opcodes.V10, ref = @Ref("Ljava/net/URLEncoder;"))
    public static String encode(String s, Charset charset) throws UnsupportedEncodingException {
        return URLEncoder.encode(s, charset.name());
    }

}
