/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.example.dto;

import lombok.*;

/**
 * @author Gunnar Morling
 */
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class GolfPlayer {

    private double handicap;
    private String name;

    // Missing fields
    private String settlement;
    private String issuer;
}
