/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asms;

import javax.microedition.rms.RecordFilter;

/**
 *
 * @author RAIDEN
 */
public class SearchFilter implements RecordFilter {

    private String searchText = null;

    public SearchFilter(String searchText) {
        this.searchText = searchText.toLowerCase();
    }

    public boolean matches(byte[] candidate) {
        String string = new String(candidate).toLowerCase();
        if (searchText != null && string.indexOf(searchText) != -1) {
            return true;
        } else {
            return false;
        }
    }

}
