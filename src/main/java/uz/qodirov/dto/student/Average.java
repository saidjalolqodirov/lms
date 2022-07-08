package uz.qodirov.dto.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * created by: Qodirov Saidjalol
 * created at: 7/8/2022 4:34 AM
 */
@Getter
@Setter
@AllArgsConstructor
public class Average implements Comparable<Average> {
    private int mark;
    private String firstName;

    @Override
    public int compareTo(Average o) {
        return Integer.compare(o.mark, mark);
    }
}
