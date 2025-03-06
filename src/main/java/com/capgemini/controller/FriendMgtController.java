package com.capgemini.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.constants.FrndMgtConstants;
import com.capgemini.exceptions.ResourceNotFoundException;
import com.capgemini.model.CommonFriendsListResp;
import com.capgemini.model.RecievesUpdatesResp;
import com.capgemini.model.Subscriber;
import com.capgemini.model.FriendsListResp;
import com.capgemini.service.FriendMgmtService;
import com.capgemini.model.FriendManagementResp;

/**
 * This controller will contain apis for friend management.
 * This class accepts new connection , getting friendlist , subscription request etc
 * @author  
 * @version 1.0
 * @since   2018-11-10 
 * 
 */



@RestController
@Validated
@RequestMapping(value = "/api/*")
public class FriendMgtController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());




	public FriendMgmtService frndMngtServc;
	FriendManagementResp fmError;

	@Autowired public FriendMgtController(FriendMgmtService frndMngtServc,FriendManagementResp fmError) {
		this.frndMngtServc=frndMngtServc;
		this.fmError=fmError;
	}





	/**
	 * This method is used to add create new connection between two emailid's
	 * @param userReq This is the json parameter contains emails
	 * @return FriendManagementResp This returns success or failure status
	 * @throws ResourceNotFoundException
	 */


		

	@RequestMapping(value = "/createfriendship", method = RequestMethod.POST)
	public ResponseEntity<FriendManagementResp> createFriendConnection(@Valid @RequestBody com.capgemini.model.CreateFriendship userReq, BindingResult results)throws ResourceNotFoundException {
		LOG.info("newFriendConnection :: ");
		FriendManagementResp frndMang = new FriendManagementResp();

		ResponseEntity<FriendManagementResp> resEnty = null;
		try{
			frndMang = frndMngtServc.createFriendConnection(userReq)	;
			String isNewfrndMangmReqSuccess = frndMang.getStatus();

            
			if(isNewfrndMangmReqSuccess.equalsIgnoreCase(FrndMgtConstants.FRND_MGMT_SUCCESS)){
				frndMang.setStatus(FrndMgtConstants.FRND_MGMT_SUCCESS);
				resEnty =  new ResponseEntity<FriendManagementResp>(frndMang, HttpStatus.OK);
			} else {
				
				
				frndMang.setStatus(FrndMgtConstants.FND_MGMT_FAILED);

			}
			resEnty =  new ResponseEntity<FriendManagementResp>(frndMang, HttpStatus.OK);

		}catch(Exception e) {
			LOG.error(e.getLocalizedMessage());
			frndMang.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
			frndMang.setDescription(FrndMgtConstants.FND_MGMT_FAILED_USERNOTEXIST);
			resEnty =  new ResponseEntity<FriendManagementResp>(frndMang, HttpStatus.SERVICE_UNAVAILABLE);

		} 

		return resEnty;


	}





	/**
	 * This method gets the friend list of requestor email id
	 * @param friendList contains emailid
	 * @param result FriendsListResp list of email ids
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/friendlist", method = RequestMethod.POST)
	public ResponseEntity<FriendsListResp> retrieveFriends(@Valid @RequestBody com.capgemini.model.FriendList friendList, BindingResult result)throws ResourceNotFoundException {
		LOG.info("--getFriendList :: " +friendList.getEmail());
		FriendsListResp frndLstResp = frndMngtServc.retrieveFriends(friendList );
		ResponseEntity<FriendsListResp> responseEnty = null;
		try{
			if(frndLstResp.getStatus() .equalsIgnoreCase(FrndMgtConstants.FRND_MGMT_SUCCESS)){
				frndLstResp.setStatus(FrndMgtConstants.FRND_MGMT_SUCCESS);
				responseEnty = new ResponseEntity<FriendsListResp>(frndLstResp, HttpStatus.OK);
			} else {
				frndLstResp.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
				responseEnty = new ResponseEntity<FriendsListResp>(frndLstResp, HttpStatus.BAD_REQUEST);

			}
		}catch(Exception e) {
			LOG.error(e.getLocalizedMessage());
			frndLstResp.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
						
			responseEnty =  new ResponseEntity<FriendsListResp>(frndLstResp, HttpStatus.SERVICE_UNAVAILABLE);

		} 
		return responseEnty;


	}


	/**
	 * 
	 * This method serves the common friends between requestor emails
	 * @param commonFrndReq containing two email ids
	 * @return CommonFriendsListResp list of common friends
	 * @throws ResourceNotFoundException
	 */

	@RequestMapping(value = "/friends", method = RequestMethod.POST)

	public ResponseEntity<CommonFriendsListResp> retrieveCommonFriendList(@Valid @RequestBody com.capgemini.model.CommonFriends  commonFrndReq, BindingResult result) throws ResourceNotFoundException {	
		LOG.info("getCommonFriendList");
		ResponseEntity<CommonFriendsListResp> respEnty = null;
		CommonFriendsListResp comFrndresp = new CommonFriendsListResp();
		try{
			comFrndresp = frndMngtServc.retrieveCommonFriendList(commonFrndReq.getFriends().get(0),commonFrndReq.getFriends().get(1) );


			if(comFrndresp.getStatus().equalsIgnoreCase(FrndMgtConstants.FRND_MGMT_SUCCESS)){
				comFrndresp.setStatus(FrndMgtConstants.FRND_MGMT_SUCCESS);
				respEnty = new ResponseEntity<CommonFriendsListResp>(comFrndresp, HttpStatus.OK);
			} else {
				comFrndresp.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
				respEnty = new ResponseEntity<CommonFriendsListResp>(comFrndresp, HttpStatus.BAD_REQUEST);

			}
		}catch(Exception e) {
			LOG.error(e.getLocalizedMessage());
			comFrndresp.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
			respEnty =  new ResponseEntity<CommonFriendsListResp>(comFrndresp, HttpStatus.SERVICE_UNAVAILABLE);

		} 
		return respEnty;
	}



	/**
	 * 
	 * @param subscriber containing requestor and target emails
	 * @param subscriber
	 * @return containing message status
	 * @throws ResourceNotFoundException
	 */


	@RequestMapping(value="/unsubscribe", method = RequestMethod.POST)
	public ResponseEntity<FriendManagementResp> unSubscribeFrnd(@Valid @RequestBody com.capgemini.model.Subscriber subscriber, BindingResult result)throws ResourceNotFoundException {
		LOG.info("unSubscribeFriend :: ");
		ResponseEntity<FriendManagementResp> respEntity = null;
		boolean isValid=validateInput(subscriber);
		if(!isValid) {
			respEntity = new ResponseEntity<FriendManagementResp>(HttpStatus.BAD_REQUEST);
		}

		FriendManagementResp fmv=null;
		try {
			fmv =frndMngtServc.unSubscribeFrnd(subscriber);
			if(fmv.getStatus().equalsIgnoreCase(FrndMgtConstants.FRND_MGMT_SUCCESS)) {
				respEntity = new ResponseEntity<FriendManagementResp>(fmv, HttpStatus.OK);
			}else {
				respEntity = new ResponseEntity<FriendManagementResp>(fmv, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			LOG.error(e.getLocalizedMessage());
			fmv.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
			respEntity = new ResponseEntity<FriendManagementResp>(fmv, HttpStatus.BAD_REQUEST);
		}

		return respEntity;
	}


	/**
	 * This mthod is used fof input validation
	 * 
	 * @param subscriber
	 * @return
	 */

	private boolean validateInput(Subscriber subscriber) {
		// TODO Auto-generated method stub
		String requestor=subscriber.getRequestor();
		String target=subscriber.getTarget();
		if(requestor==null || target==null || requestor.equalsIgnoreCase(target)) {
			return false;
		}
		return true;
	}

	/** 
	 * @param subscriber
	 * @param result
	 * @return FriendManagementResp
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value="/subscribe", method = RequestMethod.POST)
	public ResponseEntity<FriendManagementResp> subscribeFrnd(@Valid @RequestBody com.capgemini.model.Subscriber subscriber, BindingResult result)throws ResourceNotFoundException {
		LOG.info("subscribeFriend ::");


		ResponseEntity<FriendManagementResp> responseEntity = null;
		FriendManagementResp fmv = new FriendManagementResp();

		try {
			fmv =frndMngtServc.subscribeFrnd(subscriber);
			if(fmv.getStatus() == FrndMgtConstants.FRND_MGMT_SUCCESS) {
				responseEntity = new ResponseEntity<FriendManagementResp>(fmv, HttpStatus.OK);
			}else {
				responseEntity = new ResponseEntity<FriendManagementResp>(fmv, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			LOG.error(e.getLocalizedMessage());
			fmv.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
			responseEntity =  new ResponseEntity<FriendManagementResp>(fmv, HttpStatus.SERVICE_UNAVAILABLE);
		}

		return responseEntity;

	}


	
	/**
	 * 
	 * @param emailsList RecievesUpdates
	 * @param result RecievesUpdatesResp
	 * @return
	 * @throws ResourceNotFoundException
	 */


	@RequestMapping(value = "/friends/updatelist", method = RequestMethod.POST)
	public ResponseEntity<RecievesUpdatesResp> emailListRecievesUpdates(@Valid @RequestBody com.capgemini.model.RecievesUpdates emailsList, BindingResult result)throws ResourceNotFoundException {

		LOG.info("emailListRecievesupdates ::");

		ResponseEntity<RecievesUpdatesResp> respEntity = null;
		RecievesUpdatesResp response = new RecievesUpdatesResp();
		try{
			response = frndMngtServc.emailListRecievesUpdates(emailsList );

			if(response.getStatus().toString() == FrndMgtConstants.FRND_MGMT_SUCCESS){
				response.setStatus(FrndMgtConstants.FRND_MGMT_SUCCESS);
				respEntity = new ResponseEntity<RecievesUpdatesResp>(response, HttpStatus.OK);
			} else {
				response.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
				respEntity = new ResponseEntity<RecievesUpdatesResp>(response, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			LOG.error(e.getLocalizedMessage());
			response.setStatus(FrndMgtConstants.FND_MGMT_FAILED);
			respEntity =  new ResponseEntity<RecievesUpdatesResp>(response, HttpStatus.SERVICE_UNAVAILABLE);

		} 
		return respEntity;

	}



}
