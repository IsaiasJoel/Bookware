package logicaDeNegocio;

import modeloDAO.UsuarioDAO;

public class UsuarioLogic {
    UsuarioDAO daoUsuario = new UsuarioDAO();
    
    public Object[] verificarLogeo(String usuario, String password) {
        return daoUsuario.verificarLogeo(usuario, password);
    }
}
