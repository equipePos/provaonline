
package dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.FabricaConexoes;

/**
 *
 * @author Giovani
 */
public class UsuarioDAO {
    private Connection conexao;
    
    private static final String INSERIR_USUARIO =
                    "INSERT INTO `prova`.`tbl_usuario` " +
                        "(`idusuario`, " +
                        "`tbl_tipo_usuario_id_tipo_usuario`, " +
                        "`ra_rm_usuario`, " +
                        "`nome_usuario`, " +
                        "`rg_usuario`, " +
                        "`email_usuario`, " +
                        "`telefone_usuario`, " +
                        "`login_usuario`, " +
                        "`cpf_usuario`, " +
                        "`senha_usuario`) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?);";
    
    private static final  String CONSULTA_USUARIO =
			"select * from `prova`.`tbl_usuario`";
    
    private static final String AUTENTICAR_USUARIO = 
                                "select count(*) as total, tbl_tipo_usuario_id_tipo_usuario as tipo "
                                + "from `prova`.`tbl_usuario`"
                                + " where "
                                + " login_usuario = ? and "
                                + " senha_usuario = ?";
   
    public String[] getAutenticacao(String nome, String senha) throws SQLException{
        Connection conn = new FabricaConexoes().getConnection();
        PreparedStatement statement = null;
        ResultSet result = null;
        int numReg = 0;
        String[] autenticado = new String[]{"0","0"};
            try{
                statement = conn.prepareStatement(AUTENTICAR_USUARIO);
                statement.setString(1, nome);
                statement.setString(2, senha);
                result = statement.executeQuery();
                if(result.next()){
                    numReg = result.getInt("total");
                }
            }catch(SQLException e){
                e.getMessage();
            }                
            if(numReg == 1){
                    autenticado[0] = "1";
                    autenticado[1] = result.getString("tipo");
                    return autenticado;
            }

        return autenticado;			
    }
}