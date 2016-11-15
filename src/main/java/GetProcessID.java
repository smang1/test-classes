import java.lang.management.ManagementFactory;

/**
 * Created by frup66315 on 20/09/2016.
 */
public class GetProcessID {

    private static String getProcessId(final String fallback) {
        // Note: may fail in some JVM implementations
        // therefore fallback has to be provided

        // something like '<pid>@<hostname>', at least in SUN / Oracle JVMs
        final String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        final int index = jvmName.indexOf('@');

        if (index < 1) {
            // part before '@' empty (index = 0) / '@' not found (index = -1)
            return fallback;
        }

        try {
            return Long.toString(Long.parseLong(jvmName.substring(0, index)));
        } catch (NumberFormatException e) {
            // ignore
        }
        return fallback;
    }

    public static void main(String[] args) {
        String pName=ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(ManagementFactory.getRuntimeMXBean().getName());
        System.out.println(getProcessId("NA"));
        System.out.println(System.getProperty(pName));

    }
}
