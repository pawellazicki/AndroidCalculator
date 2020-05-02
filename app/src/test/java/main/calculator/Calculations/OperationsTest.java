package main.calculator.Calculations;

import org.junit.Test;

import static org.junit.Assert.*;

public class OperationsTest {

    @Test
    public void operation1() {
        Operations operations = new Operations();
        operations.appendNumber(String.valueOf(2));
        operations.operation('+');
        operations.appendNumber(String.valueOf(3));
        double res = operations.operation('=');
        assertEquals(5, res, 0.0);
    }

    @Test
    public void operation2() {
        Operations operations = new Operations();
        operations.appendNumber(String.valueOf(2));
        operations.operation('-');
        operations.appendNumber(String.valueOf(3));
        double res = operations.operation('=');
        assertEquals(-1, res, 0.0);
    }

    @Test
    public void testOperation3() {
        Operations operations = new Operations();
        operations.appendNumber(String.valueOf(2));
        operations.operation('*');
        operations.appendNumber(String.valueOf(3));
        double res = operations.operation('=');
        assertEquals(6, res, 0.0);
    }

    @Test
    public void testOperation4() {
        Operations operations = new Operations();
        operations.appendNumber(String.valueOf(3));
        operations.operation('/');
        operations.appendNumber(String.valueOf(3));
        double res = operations.operation('=');
        System.out.println("2/3 = " + res);
    }

    @Test
    public void testOperation5() {
        Operations operations = new Operations();
        operations.appendNumber(String.valueOf(3));
        operations.operation('-');
        operations.appendNumber(String.valueOf(2));
        operations.operation('-');
        operations.appendNumber(String.valueOf(1));
        double res = operations.operation('=');
        assertEquals(0,res,0.0);
    }

    @Test
    public void testOperation6() {
        Operations operations = new Operations();
        operations.appendNumber(String.valueOf(3));
        operations.operation('-');
        operations.appendNumber(String.valueOf(3));
        operations.operation('-');
        operations.appendNumber(String.valueOf(3));
        operations.operation('-');
        operations.appendNumber(String.valueOf(3));
        operations.operation('-');
        operations.appendNumber(String.valueOf(3));
        double res = operations.operation('=');
        assertEquals(-9,res,0.0);
    }

    @Test
    public void testOperation7() {
        Operations operations = new Operations();
        operations.appendNumber(String.valueOf(3));
        operations.operation('+');
        operations.appendNumber(String.valueOf(3));
        operations.operation('*');
        operations.appendNumber(String.valueOf(2));
        operations.operation('-');
        operations.appendNumber(String.valueOf(5));
        operations.operation('+');
        operations.appendNumber(String.valueOf(7));
        double res = operations.operation('=');
        assertEquals(14,res,0.0);
    }

    @Test
    public void testOperation8() {
        Operations operations = new Operations();
        operations.operation('-');
        operations.appendNumber(String.valueOf(2));
        operations.operation('-');
        operations.appendNumber(String.valueOf(3));
        double res = operations.operation('=');
        assertEquals(-5, res, 0.0);
    }
}