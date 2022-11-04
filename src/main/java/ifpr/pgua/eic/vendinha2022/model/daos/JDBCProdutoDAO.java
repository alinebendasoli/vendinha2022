package ifpr.pgua.eic.vendinha2022.model.daos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.vendinha2022.model.FabricaConexao;
import ifpr.pgua.eic.vendinha2022.model.entities.Produto;
import ifpr.pgua.eic.vendinha2022.model.results.Result;

public class JDBCProdutoDAO implements ProdutoDAO {
    
    private FabricaConexao fabricaConexao;

    public JDBCProdutoDAO(FabricaConexao fabricaConexao){
        this.fabricaConexao = fabricaConexao;
    }

    public Result criar(Produto produto) {
        try{
            
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("INSERT INTO produtos(nome,descricao,valor,quantidadeEstoque) VALUES (?,?,?,?)");

        
            pstm.setString(1, produto.getNome());
            pstm.setString(2, produto.getDescricao());
            pstm.setDouble(3, produto.getValor());
            pstm.setDouble(4, produto.getQuantidadeEstoque());

            pstm.executeUpdate();

            pstm.close();
            con.close();

            return Result.success("Produto criado com sucesso!");
            
        }catch(SQLException nomeQueQuiser){
            System.out.println(nomeQueQuiser.getMessage());
            return Result.fail(nomeQueQuiser.getMessage());
        }    
    }

    public Result atualizar(int id, Produto produto) {
        try{
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("UPDATE produtos set valor=?, quantidadeEstoque=? WHERE id=?");

            pstm.setDouble(1, produto.getValor());
            pstm.setDouble(2, produto.getQuantidadeEstoque());
            pstm.setInt(3, id);
            
            pstm.executeUpdate();

            pstm.close();
            con.close();

            return Result.success("Produto atualizado com sucesso!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return Result.fail(e.getMessage());
        }
        
    }

    @Override
    public Produto buscarPorId(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Produto> buscarTodos() {
        ArrayList<Produto> produto = new ArrayList<>();
        try{

            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM produtos");

            ResultSet rs = pstm.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                Double valor = rs.getDouble("valor");
                Double quantidadeEstoque = rs.getDouble("quantidadeEstoque");

                Produto produtos = new Produto(id, nome, descricao, valor, quantidadeEstoque);

                produto.add(produtos);
            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }  

        return Collections.unmodifiableList(produto);
        
    }

    @Override
    public Result remover(int id) {
        // TODO Auto-generated method stub
        return null;
    }





}
