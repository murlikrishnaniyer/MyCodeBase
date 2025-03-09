# MyCodeBase

https://medium.com/javarevisited/deploying-a-spring-boot-application-on-kubernetes-on-aws-and-gcp-7cf90043882e


1 docker build -t spring-boot-k8s .

2 $ eksctl create cluster --name my-cluster --version 1.18 --region us-west-2 --nodegroup-name my-nodes --node-type t3.medium --nodes 3

3 create deployment.yaml
	apiVersion: apps/v1
kind: Deployment
metadata:
  name: spring-boot-k8s
spec:
  replicas: 3
  selector:
    matchLabels:
      app: spring-boot-k8s
  template:
    metadata:
      labels:
        app: spring-boot-k8s
    spec:
      containers:
        - name: spring-boot-k8s
          image: spring-boot-k8s
          ports:
            - containerPort: 8080
            
4 $ kubectl apply -f deployment.yaml
5 Create a file called service.yaml
   apiVersion: v1
kind: Service
metadata:
  name: spring-boot-k8s
spec:
  selector:
    app: spring-boot-k8s
  ports:
    - protocol: TCP
      port: 80
      targetPort: 8080
  type: LoadBalancer
  
6 $ kubectl get svc
