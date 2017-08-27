/**
 * Created by ultra on 01.08.2017.
 */
public class Main {
    public static void main(String[] args)
    {
        TreeNode a = new TreeNodeImpl();

        TreeNode aa = new TreeNodeImpl();
        TreeNode ab = new TreeNodeImpl();
        TreeNode ac = new TreeNodeImpl();

        TreeNode aaa = new TreeNodeImpl();
        TreeNode aab = new TreeNodeImpl();

        TreeNode aba = new TreeNodeImpl();
        TreeNode abb = new TreeNodeImpl();

        TreeNode aca = new TreeNodeImpl();
        TreeNode acb = new TreeNodeImpl();
        TreeNode acc = new TreeNodeImpl();

        a.addChild(aa);
        a.addChild(ab);
        a.addChild(ac);

        aa.addChild(aaa);
        aa.addChild(aab);

        ab.addChild(aba);
        ab.addChild(abb);

        ac.addChild(aca);
        ac.addChild(acb);
        ac.addChild(acc);

        a.setData("a");

        aa.setData("aa");
        ab.setData("ab");
        ac.setData("ac");

        aaa.setData("aaa");
        aab.setData("aab");

        aba.setData("aba");
        abb.setData("abb");

        aca.setData("aca");
        acb.setData("acb");
        acc.setData("acc");

        System.out.println(acc.findChild("a"));

        System.out.println(a.findChild("aaa"));

        System.out.println(a.isLeaf());
        System.out.println(acc.isLeaf());

        System.out.println(a.getChildCount());

        System.out.println(acc.getTreePath());

        //System.out.println(a.findChild("d").getData());

        //System.out.println(acc.findParent("d").getData());

        System.out.println(a.getRoot().getData());



    }
}
