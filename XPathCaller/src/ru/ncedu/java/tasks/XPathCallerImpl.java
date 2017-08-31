package ru.ncedu.java.tasks;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

public class XPathCallerImpl implements XPathCaller {
    public static void main(String[] args) throws Exception{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document src1 = builder.parse(System.getProperty("user.dir") + "\\src\\ru\\ncedu\\java\\tasks\\emp.xml");
        Document src2 = builder.parse(System.getProperty("user.dir") + "\\src\\ru\\ncedu\\java\\tasks\\emp-hier.xml");

        XPathCallerImpl test = new XPathCallerImpl();

        System.out.println("Test1:");
        Element [] elements1 = test.getCoworkers(src1, "7521", "emp");
        for (int i = 0; i < elements1.length; i++)
            System.out.println(elements1[i].getElementsByTagName("job").item(0).getTextContent());

        System.out.println("Test2:");
        Element [] elements2 = test.getCoworkers(src2, "7521", "emp-hier");
        for (int i = 0; i < elements2.length; i++)
            System.out.println(elements2[i].getElementsByTagName("job").item(0).getTextContent());
    }

    /**
     * Converts NodeList to Elements array
     * @param nodeList nodeList
     * @return elements array
     */
    private Element[] nodeListToElementsArray(NodeList nodeList){
        Element[] elements = new Element[nodeList.getLength()];
        for (int i = 0; i < nodeList.getLength(); i++) {
            elements[i] = (Element)nodeList.item(i);
        }
        return elements;
    }
    /**
     * For given department choose all its employees
     *
     * @param src     XML document for search
     * @param deptno  department number depno
     * @param docType "emp" - for file emp.xml; "emp-hier" - for file emp-hier.xml
     */
    @Override
    public Element[] getEmployees(Document src, String deptno, String docType) {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        NodeList nodes = null;
        try {
            XPathExpression expression = xpath.compile("//employee[@deptno = '" + deptno + "']");
            Object result = expression.evaluate(src, XPathConstants.NODESET);
            nodes = (NodeList) result;
        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
        }
        return nodeListToElementsArray(nodes);
    }

    /**
     * Choose the highest paid employee
     *
     * @param src     XML document to search
     * @param docType "emp" - for file emp.xml; "emp-hier" - for file emp-hier.xml
     */
    @Override
    public String getHighestPayed(Document src, String docType) {
        XPathFactory xpathfactory = XPathFactory.newInstance();
            XPath xpath = xpathfactory.newXPath();
            //list of salaries
            NodeList salaries;
            //name of the highest payed employee
            String name = "";
            try {
                //fetching max salary
                XPathExpression expression = xpath.compile("//employee/sal/text()");
                salaries = (NodeList) expression.evaluate(src, XPathConstants.NODESET);
                //maximum salary
                Double max = 0.0;
                for (int i = 0; i < salaries.getLength(); i++) {
                    if (max < Double.parseDouble(salaries.item(i).getNodeValue()))
                        max = Double.parseDouble(salaries.item(i).getNodeValue());
                }

                //fetching name of employee with max salary
                expression = xpath.compile("//employee[sal = " + max.toString() + "]/ename/text()");
                name = (String)expression.evaluate(src, XPathConstants.STRING);
            } catch (XPathExpressionException ex) {
                ex.printStackTrace();
        }
        return name;
    }

    /**
     * Choose the highest paid employee
     *
     * @param src     XML document for search
     * @param deptno  department number depno
     * @param docType "emp" - for file emp.xml; "emp-hier" - for file emp-hier.xml
     */
    @Override
    public String getHighestPayed(Document src, String deptno, String docType) {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        //list of salaries
        NodeList salaries;
        //name of the highest payed employee
        String name = "";
        try {
            //fetching max salary according to department number
            XPathExpression expression = xpath.compile("//employee[@deptno = '" + deptno + "']/sal/text()");
            salaries = (NodeList) expression.evaluate(src, XPathConstants.NODESET);
            //maximum salary
            Double max = 0.0;
            for (int i = 0; i < salaries.getLength(); i++) {
                if (max < Double.parseDouble(salaries.item(i).getNodeValue()))
                    max = Double.parseDouble(salaries.item(i).getNodeValue());
            }

            //fetching name of employee with max salary and corresponding department number
            expression = xpath.compile("//employee[@deptno = '" + deptno + "' and ./sal = " + max.toString() + "]/ename/text()");
            name = (String)expression.evaluate(src, XPathConstants.STRING);
        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
        }
        return name;
    }

    /**
     * Choose all top-managers
     * Consider manager as a top if he has no manager above
     *
     * @param src     XML document to search
     * @param docType "emp" - for file emp.xml; "emp-hier" - for file emp-hier.xml
     */
    @Override
    public Element[] getTopManagement(Document src, String docType) {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        NodeList nodes = null;
        try {
            if (docType.equals("emp")) {
                XPathExpression expression = xpath.compile("//employee[not(@mgr)]");
                nodes = (NodeList) expression.evaluate(src, XPathConstants.NODESET);
            } else
            if (docType.equals("emp-hier")) {
                XPathExpression expression = xpath.compile("/employee");
                nodes = (NodeList) expression.evaluate(src, XPathConstants.NODESET);
            }
        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
        }
        return nodeListToElementsArray(nodes);
    }

    /**
     * Chose all employees who are not managers
     * Consider that employee has not any secondaries
     *
     * @param src     XML document to search
     * @param docType "emp" - for file emp.xml; "emp-hier" - for file emp-hier.xml
     */
    @Override
    public Element[] getOrdinaryEmployees(Document src, String docType) {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        NodeList nodes = null;
        try {
            if (docType.equals("emp")) {
                XPathExpression expression = xpath.compile("//employee[not(@empno = (//@mgr))]");
                nodes = (NodeList) expression.evaluate(src, XPathConstants.NODESET);
            } else
            if (docType.equals("emp-hier")) {
                XPathExpression expression = xpath.compile( "//employee[not(./employee)]");
                nodes = (NodeList) expression.evaluate(src, XPathConstants.NODESET);
            }
        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
        }
        return nodeListToElementsArray(nodes);
    }

    /**
     * For given empno find all his colleges who have the same manager
     *
     * @param src     XML document for search
     * @param empno   Number of employees empno
     * @param docType "emp" - for file emp.xml; "emp-hier" - for file emp-hier.xml
     */
    @Override
    public Element[] getCoworkers(Document src, String empno, String docType) {
        XPathFactory xpathfactory = XPathFactory.newInstance();
        XPath xpath = xpathfactory.newXPath();
        NodeList nodes = null;
        try {
            if (docType.equals("emp")) {
                XPathExpression expression = xpath.compile("//employee[(@empno != " + empno + ") and (@mgr = //employee[@empno = " + empno + "]/@mgr)]");
                nodes = (NodeList) expression.evaluate(src, XPathConstants.NODESET);
            } else
            if (docType.equals("emp-hier")) {
                XPathExpression expression = xpath.compile( "//employee[./employee[@empno = '" + empno + "']]/employee[@empno != '" + empno + "']");
                nodes = (NodeList) expression.evaluate(src, XPathConstants.NODESET);
            }
        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
        }
        return nodeListToElementsArray(nodes);
    }
}
