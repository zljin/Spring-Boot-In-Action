package com.zljin.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateCodeUtilsTest {
    @Test
    public void testGenerateCode() {
        String code = ValidateCodeUtils.generateCode();
        assertNotNull(code, "Code should not be null");
        assertEquals(4, code.length(), "Code length should be 4");
    }

    @Test
    public void testGenerateCodeWithLength() {
        String code = ValidateCodeUtils.generateCode(4);
        assertNotNull(code, "Code should not be null");
        assertEquals(4, code.length(), "Code length should be 4");

        code = ValidateCodeUtils.generateCode(6);
        assertNotNull(code, "Code should not be null");
        assertEquals(6, code.length(), "Code length should be 6");
    }

    @Test
    public void testGenerateValidateCodeWithLength4() {
        String code = ValidateCodeUtils.generateValidateCode(4);
        assertNotNull(code, "Code should not be null");
        assertEquals(4, code.length(), "Code length should be 4");
        assertTrue(code.matches("\\d{4}"), "Code should only contain digits");
    }

    @Test
    public void testGenerateValidateCodeWithLength6() {
        String code = ValidateCodeUtils.generateValidateCode(6);
        assertNotNull(code, "Code should not be null");
        assertEquals(6, code.length(), "Code length should be 6");
        assertTrue(code.matches("\\d{6}"), "Code should only contain digits");
    }

    @Test
    public void testGenerateValidateCodeInvalidLength() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            ValidateCodeUtils.generateValidateCode(5);
        });
        assertEquals("只能生成4位或6位数字验证码", exception.getMessage());
    }

}