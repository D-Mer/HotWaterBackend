/**
 * @author jh
 * @date 2022/4/15 22:20
 */
public class Test {

    public static void main(String[] args) {
        String host = "http://172.29.7.40:8000/api/user/avatar/userId=4";
        int i = host.indexOf('/', 7);
        host = host.substring(0, i);
        System.out.println(host);
    }
}
