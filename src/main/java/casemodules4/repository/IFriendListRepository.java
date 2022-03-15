package casemodules4.repository;

import casemodules4.model.FriendList;
import casemodules4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IFriendListRepository extends JpaRepository<FriendList, Long> {

    FriendList deleteByUserFromAndUserTo(User userFrom, User userTo);

    @Modifying
    @Query(value = "UPDATE friend_list SET status = 'friend' WHERE user_from_id_user = :userFromId AND user_to_id_user = :userToId", nativeQuery = true)
    void acceptFriendRequest(@Param("userFromId") Long userFromId,@Param("userToId") Long userToId);

    @Modifying
    @Query(value = "UPDATE friend_list SET status = 'block' WHERE user_from_id_user = :userFromId AND user_to_id_user = :userToId", nativeQuery = true)
    void blockFriendRequest(Long userFromId, Long userToId);
}