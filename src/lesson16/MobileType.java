package lesson16;

import java.security.PublicKey;

public enum MobileType {
    ANDROID("Android G5", 7),
    IOS("Iphone 6 plus", 15);

    private final String name;
    private final int version;
    MobileType(String name, int version){
        this.name = name;
        this.version = version;

    }
    public String getName(){
        return this.name;
    }
    public int getVersion(){
        return this.version;
    }
}
