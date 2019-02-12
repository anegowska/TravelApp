package com.anegowska.publishers;

import com.anegowska.model.enums.Board;

import javax.ejb.Stateless;
import java.util.EnumSet;
import java.util.Map;

@Stateless
public class BoardTypePublisher {

    public void publishAllBoardTypes(Map<String, Object> model) {

        EnumSet<Board> boardTypes = EnumSet.allOf(Board.class);
        model.put("boardTypes", boardTypes);
    }
}
