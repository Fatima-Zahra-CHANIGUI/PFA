package ma.oujda.ump.ensao.autogo;

import java.util.ArrayList;
import java.util.List;

public class AStarExecute {
	
	private List<Node> path;
	static String commandes;

    public List<Node> getPath() {
		return path;
	}

	public AStarExecute(int rows , int cols , Node initialNode , Node finalNode , ArrayList<Node> blocksArray) {
        AStar aStar = new AStar(rows, cols, initialNode, finalNode);
        aStar.setBlocks(blocksArray);
        path = aStar.findPath();
      
        List<String> listCMD = pathToListCommandes(path);
        
        String cmdFinale = "";
        for (String currentCMD : listCMD)
        {
        	cmdFinale += currentCMD;
		}
        commandes = cmdFinale;
        
        System.out.println("commandes :"+cmdFinale);
    }

	private static List<String> pathToListCommandes(List<Node> path) {
		
		List<String> listCMD = new ArrayList<String>();
		if(path != null && !path.isEmpty())
		{
			int currentDirection = 0;
			int oldDirection = 6;
			System.out.println(path.get(0));
			for (int i = 1; i < path.size(); i++) 
			{
				System.out.println(path.get(i));
				currentDirection = getCurrentDirection(path.get(i-1), path.get(i));
				if(oldDirection == 0 || (oldDirection == currentDirection))
				{
					listCMD.add("8");
				}
				else if(6 == oldDirection)
				{
					if(2 == currentDirection)
					{
						listCMD.add("68");
					} else
					{
						listCMD.add("48");
					}
				}
				else if(4 == oldDirection)
				{
					if(2 == currentDirection)
					{
						listCMD.add("48");
					} else
					{
						listCMD.add("68");
					}
				}
				else if(8 == oldDirection)
				{
					if(6 == currentDirection)
					{
						listCMD.add("68");
					} else
					{
						listCMD.add("48");
					}
				}
				else if(2 == oldDirection)
				{
					if(6 == currentDirection)
					{
						listCMD.add("48");
					} else
					{
						listCMD.add("68");
					}
				}
				oldDirection = currentDirection;	
			}
		}
		return listCMD;
	}

	private static int getCurrentDirection(Node nodePresedent, Node nodeCourant) {
		int currentDirection = 0;
		
		if(nodePresedent.getRow() == nodeCourant.getRow()) {
			if(nodePresedent.getCol() < nodeCourant.getCol()) {currentDirection = 6;  }
			else {currentDirection = 4;  } 
		}
		else if(nodePresedent.getCol() == nodeCourant.getCol()) {
			if(nodePresedent.getRow() < nodeCourant.getRow()) {currentDirection = 2;  }
			else {currentDirection = 8;  }
		}
		
		return currentDirection;
	}
}
