package ifpr.pgua.eic.vendinha2022;

import ifpr.pgua.eic.vendinha2022.model.FabricaConexao;
import ifpr.pgua.eic.vendinha2022.model.daos.JDBCProdutoDAO;
import ifpr.pgua.eic.vendinha2022.model.daos.ProdutoDAO;
import ifpr.pgua.eic.vendinha2022.model.entities.Produto;

public class Teste {
    
    public static void main(String[] args) {
        ProdutoDAO dao = new JDBCProdutoDAO(FabricaConexao.getInstance());

        Produto garrafa = new Produto("Rosa","Plástico", 15, 10);

        dao.atualizar(1,garrafa);
    }
}
