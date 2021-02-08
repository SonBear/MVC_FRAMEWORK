/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mvc.framework.logger.constants;

/**
 *
 * @author emman
 */
public enum SizeFiles {

    SIZE_GB("gb"),
    SIZE_KB("kb"),
    SIZE_MG("mb");

    private final String value;

    SizeFiles(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
