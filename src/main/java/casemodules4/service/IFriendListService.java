package casemodules4.service;

import casemodules4.model.FriendList;

import java.util.List;

public interface IFriendListService {
    List<FriendList> findAll();

    FriendList findById(Long id);

    FriendList save(FriendList friendList);

    void unFriend(Long userFirstId, Long userSecondId);

    void sendFriendRequest(FriendList friendList);

    void acceptFriendRequest(Long userFromId, Long userToId);

    void blockFriend(Long userFromId, Long userToId);

    String checkFriendStatus(Long userFirstId, Long userSecondId);
}