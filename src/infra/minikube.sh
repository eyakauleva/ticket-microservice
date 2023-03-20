#! /bin/bash

kubectl apply -f pg-secrets.yaml
kubectl apply -f pg-service.yaml
kubectl apply -f pg-statefulset.yaml

kubectl apply -f app-configmap.yaml
kubectl apply -f app-service.yaml
kubectl apply -f app-deployment.yaml

minikube service tickets-service