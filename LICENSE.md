## JvmDowngrader

Copyright (C) 2024 William Gray <jvmdowngrader@wagyourtail.xyz>

This program is provided under 2 licenses:

* [The GNU Lesser General Public License version 2.1](license/LGPLv2.1.md)
* [A Commercial And Support License Agreement](license/COMMERCIAL.md)

If you are a non-commercial user, it is recommended to use the LGPLv2.1 license, as it is more permissive and allows you
to use the software for free.
If you are a commercial user, or need support with your use of the product, a commercial and support license is
available for purchase by contacting me at the email address above.

If you are a non-commercial user, or your usecase is covered by the LGPLv2.1 license,
I would appreciate it if you would consider donating to support the project, but it is in no way required.

### LGPLv2.1 License Concerns

Some people think that shading would mean they're bound by the stricter GPL license due to the inclusion of
jvmdowngrader's java class files. I don't believe this to be the case.

For the purpose of Licensing, the produced jar from this task, or the downgrading task, should be considered a "Combined
Work",
as it contains the original code from the input jar and the shaded code from jvmdowngrader's api.

And this does, usually, mean that you shouldn't need to use the *exact* same license.
Running this tool, should be a thing the end-user is capable of doing, thus section 6.a should be satisfied as long as
your project provides the unshaded/undowngraded jar as well, or alternatively provides source code to build said jar, or
the post-shaded jar.
