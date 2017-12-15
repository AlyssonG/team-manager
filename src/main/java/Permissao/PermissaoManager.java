package Permissao;

import java.util.ArrayList;

public class PermissaoManager {
    private static PermissaoManager manager = new PermissaoManager();
    ArrayList<String> presentPermissions = new ArrayList<>();

    private PermissaoManager(){

    }

    public static PermissaoManager getInstance(){
        return manager;
    }

    public void addPermission(String permission){
        presentPermissions.add(permission);
    }

    public boolean hasPermission(String annotation){
        if(annotation == null){
            return true;
        } else{
            for(String s : presentPermissions) {
                if (annotation.toLowerCase().contains(s.toLowerCase())){
                    return true;
                }
            }
            return false;
        }
    }
}
