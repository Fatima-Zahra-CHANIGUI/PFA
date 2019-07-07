package ma.oujda.ump.ensao.autogo;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AutoObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private int rows, columns;
    private Node d, f =null ;
    private ArrayList<Node> block;
    private List<Node> path;

    public AutoObject(){
        rows=0;
        columns=0;
        d=null;
        f=null;
        block=null;
        path=null;
    }

    public AutoObject(int rows, int columns, Node d, Node f, ArrayList<Node> block){
        super();
        this.rows=rows;
        this.columns=columns;
        this.d=d;
        this.f=f;
        this.block=block;
    }

    @Override
    public String toString(){
        return "Votre espace est composé de :\n\n" + this.rows + " lignes et " + this.columns + " colonnes \n\n"
                +"Le départ : "+this.d+"\n"
                +"L'arrivée : "+this.f+"\n\n"
                +"Les obstacles :\n" + this.block.toString()+"\n\n"
                +"Le plus court chemin : \n"+this.path+"\n";
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Node getD() {
        return d;
    }

    public void setD(Node d) {
        this.d = d;
    }

    public Node getF() {
        return f;
    }

    public void setF(Node f) {
        this.f = f;
    }

    public ArrayList<Node> getBlock() {
        return block;
    }

    public void setBlock(ArrayList<Node> block) {
        this.block = block;
    }

    public List<Node> getPath() {
        return path;
    }

    public void setPath(List<Node> path) {
        this.path = path;
    }
}