
package DAL;

/**
 *
 * @author Eugen
 */
public class RepoFactory {
    
    public static IRepo GetRepo(int tip){
        switch (tip) {
            case 1:
                return new SqlRepo();
            default:
                return null;
        }
    } 
    
}
