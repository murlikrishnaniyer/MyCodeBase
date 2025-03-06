package com.capgemini.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.exceptions.ResourceNotFoundException;
import com.capgemini.model.CommonFriendsListResp;
import com.capgemini.model.RecievesUpdatesResp;
import com.capgemini.model.Subscriber;
import com.capgemini.model.FriendsListResp;
import com.capgemini.repository.FriendMangmtRepo;
import com.capgemini.model.FriendManagementResp;
/**
 * This controller will contain apis for friend management.
 * This class accepts new connection , getting friendlist , subscription request etc
 * @author  sanjmand
 * @version 1.0
 * @since   2018-11-10 
 * 
 */
@Service
public class FriendMgmtService {

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	@Autowired
	FriendMangmtRepo friendMangmtRepo;
	@Autowired public FriendMgmtService(FriendMangmtRepo friendMangmtRepo) {
		this.friendMangmtRepo=friendMangmtRepo;
	} 

	/**
	 * This a service method creates a new connection
	 * @param userReq containing request parameter
	 * @return FriendManagementValidation  response object containing status code and error message 
	 */

	public FriendManagementResp createFriendConnection(com.capgemini.model.CreateFriendship userReq) throws ResourceNotFoundException {
		LOG.info("service class method addNewFriendConnection");
		FriendManagementResp fmResponse = friendMangmtRepo.createFriendConnection(userReq);
		return fmResponse;
	}

	/**
	 * 
	 * @param friendListRequest containg requestor email
	 * @return list of email addresses
	 * @throws ResourceNotFoundException
	 */


	public FriendsListResp retrieveFriends(com.capgemini.model.FriendList friendListRequest) throws ResourceNotFoundException {
		LOG.info("service class method  getFriendList");
		return friendMangmtRepo.getFriendsList(friendListRequest);


	}


	/**
	 * 
	 * @param email1
	 * @param email2
	 * @return list of common emails
	 * @throws ResourceNotFoundException
	 */


	public CommonFriendsListResp retrieveCommonFriendList(String email1 ,String email2) throws ResourceNotFoundException {
		LOG.info("service class method  retrieveCommonFriendList");
		return friendMangmtRepo.retrieveCommonFriendList(email1,email2);
	}



	/**
	 * 
	 * @param subscriber containing requestor and unsubscribed emailid
	 * @return FriendManagementValidation containing success message
	 * @throws ResourceNotFoundException
	 */

	public FriendManagementResp unSubscribeFrnd(Subscriber subscriber) throws ResourceNotFoundException {
		LOG.info("service class method  unSubscribeTargetFriend");
		return friendMangmtRepo.unSubscribeTargetFriend(subscriber);
	}

	/**
	 * 
	 * @param subscriber
	 * @return FriendManagementResp
	 * @throws ResourceNotFoundException
	 */

	public FriendManagementResp subscribeFrnd(com.capgemini.model.Subscriber subscriber)throws ResourceNotFoundException {

		LOG.info("service class method  subscribeFrnd");
		return friendMangmtRepo.subscribeTargetFriend(subscriber);

	}

	/**
	 * 
	 * @param emailsList RecievesUpdates
	 * @return RecievesUpdatesResp
	 * @throws ResourceNotFoundException
	 */

	public RecievesUpdatesResp emailListRecievesUpdates(com.capgemini.model.RecievesUpdates emailsList )throws ResourceNotFoundException {

		LOG.info("service class method  emailListRecievesUpdates");
		return friendMangmtRepo.emailListRecievesupdates(emailsList);
	}


}
