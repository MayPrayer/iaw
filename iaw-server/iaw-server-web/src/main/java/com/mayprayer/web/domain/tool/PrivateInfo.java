package com.mayprayer.web.domain.tool;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: shiwx
 * @Description: TODO
 * @Date: Created in 10:18 2024/3/24
 * @Modified By:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrivateInfo {

    private List<String> addresses;
    private List<String> emails;
    private List<String> id_numbers;
    private List<String> names;
    private List<String> nicknames;

    private List<String> passwords;
    private List<String> phone_numbers;
    private List<String> qq_numbers;
    private List<String> wb_numbers;

}
