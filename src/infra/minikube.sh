#! /bin/bash

#kubectl apply -f mongodb-secrets.yaml
#kubectl apply -f mongodb-service.yaml
#kubectl apply -f mongodb-statefulset.yaml

kubectl apply -f app-configmap.yaml
kubectl apply -f app-service.yaml
kubectl apply -f app-deployment.yaml

#minikube service ticket-service