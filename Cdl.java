import java.util.*;
import java.io.*;

public class Cdl {

	public static void main(String [] args) {

		Cuvant [] all = new Cuvant[10000000];
		for(int i = 0; i < 10000000; i++)
			all[i] = new Cuvant();
		int count = 0;
		int j = 0;
		String aux;
		boolean ver = true;
		try
		{
			PrintWriter writer = new PrintWriter("output.txt");
			for(int i = 0; i < args.length ; i++)
			{
				File file = new File(args[i]);
				try {
					Scanner input = new Scanner(file);

					count = 0;
					while(input.hasNext()) {
						aux = input.next().toLowerCase();
						for(int k = 0; k < aux.length(); k++)
						{
							if(aux.charAt(k) == '\'')
							{
							//	ver = false;
								aux = aux.substring(0,k);
								break;
							}
						}
						if(ver)
						{
							all[j].cuv = aux.replaceAll("[<>.':?=+!\"\\[\\],;()1234567809]","");
							all[j].file.add(i+1);
							j++;
						}
						ver = true;
						count++;
					}
				}

				catch(Exception e)
				{
					e.printStackTrace();
				}
			}

		//	System.out.println("Finish reading!");
			int nr_for_search = j;

			Scanner in = new Scanner(System.in);
			String [] search = new String[10000];
			j = 0;

			File file = new File(args[args.length - 1]);
			try {
					Scanner input = new Scanner(System.in);
					while(input.hasNext())
					{
						search[j] = input.next();
						if(search[j].equals("/exit"))
							break;
						j++;
					}


			}
			catch(Exception e)
			{
				e.printStackTrace();
			}


			int nr_inputs = j;

			int i;
			int poz;
			int k = 0;
			int l;
			Vector<Cuvant> results = new Vector<Cuvant>();
			Vector<Cuvant> result_final = new Vector<Cuvant>();

			for(i = 0; i < nr_inputs; i++)
			{
				for(j = 0; j < nr_for_search; j++)
				{

					poz = all[j].cuv.indexOf(search[i]);

//					if(search[i].equals("no") && poz != -1)
//						System.out.println(all[j].cuv + " " + poz);

					if(poz == -1)
						continue;
					if(poz == 0)
					{
						results.add(all[j]);
					}
				  else if(checkFor(all[j].cuv,search[i]))        //	else if(all[j].cuv.charAt(poz-1) == '-')
					{
						results.add(all[j]);
					}
				}
					Collections.sort(results,new Comparator<Cuvant>() {
						public int compare(Cuvant ob1, Cuvant ob2) {

							Cuvant cuv1 = (Cuvant)ob1;
							Cuvant cuv2 = (Cuvant)ob2;

							return cuv1.cuv.compareTo(cuv2.cuv);
						}

					});


					boolean ok = false;
					for(k = 0; k < results.size(); k++)
					{
						for(l = 0 ; l < result_final.size(); l++)
						{
							if(result_final.get(l).cuv.equals(results.get(k).cuv))
							{
								ok = true;
								break;
							}
						}
						if(ok)
						{
							//result_final.get(l).file.add(results.get(k).file.get(0));
							if(!result_final.get(l).file.contains(results.get(k).file.get(0)))
								result_final.get(l).file.add(results.get(k).file.get(0));
						}
						else
						{
							result_final.add(results.get(k));
						}
						ok = false;

					}
			//		writer.println(search[i]);
					for(k = 0;( k < 5 && k < result_final.size()); k++)
					{
					//	writer.println(result_final.get(k).toString());
							System.out.println(result_final.get(k).toString());
					}

					if(result_final.size() == 0) {
					//	writer.println("No suggestions...");
						System.out.println("No suggestions...");
					}
					results.clear();
					result_final.clear();
				//writer.println();
				System.out.println();

			}
				writer.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}



	//			System.out.println(count);

	}

	public static boolean checkFor(String s, String x)
	{
			int poz;
			x = "-" + x;
			poz = s.indexOf(x);
			if(poz != -1)
				return true;
			else
				return false;
	}
}


class Cuvant {

	public String cuv;
	public Vector<Integer> file = new Vector<Integer>();

	public String toString()
	{
		String out = cuv + " : ";
		for(int i = 0 ; i < file.size(); i++)
			out = out + file.get(i) + " ";

		return out;
	}

}
