#!/bin/sh

#cp -rf *.js ../tjespring/swptest/t/
cp -rf * ../tjespring/swptest/t/

cd ../tjespring/swptest

git add --all
git commit -am "node"
git push
